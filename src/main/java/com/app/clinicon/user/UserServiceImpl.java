package com.app.clinicon.user;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityNotFoundException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.clinicon.activationtoken.ActivationToken;
import com.app.clinicon.activationtoken.ActivationTokenService;
import com.app.clinicon.appointment.AppointmentDAO;
import com.app.clinicon.attentionrating.AttentionRating;
import com.app.clinicon.attentionrating.AttentionRatingDAO;
import com.app.clinicon.diseasesTreated.DiseaseTreated;
import com.app.clinicon.diseasesTreated.DiseaseTreatedService;
import com.app.clinicon.exception.EntityAlreadyExistsException;
import com.app.clinicon.jwt.JwtProvider;
import com.app.clinicon.mail.TemplateMailSender;
import com.app.clinicon.requestsdto.LoginRequestDTO;
import com.app.clinicon.requestsdto.RegisterRequestDTO;
import com.app.clinicon.responsesdto.DashboardResponseDTO;
import com.app.clinicon.responsesdto.LoginResponseDTO;
import com.app.clinicon.role.Role;
import com.app.clinicon.role.RoleName;
import com.app.clinicon.role.RoleService;
import com.app.clinicon.specialty.SpecialtyService;
import com.app.clinicon.upload.CloudinaryUpload;
import com.app.clinicon.userspecialty.UserSpecialty;
import com.app.clinicon.util.DateUtils;
import com.app.clinicon.util.TextUtils;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    private final RoleService roleService;
    private final ActivationTokenService activationTokenService;
    private final SpecialtyService specialtyService;
    private final DiseaseTreatedService diseaseTreatedService;
    private final AttentionRatingDAO attentionRatingDAO;
    private final AppointmentDAO appointmentDAO;

    private final JwtProvider jwtProvider;
    private final TemplateMailSender templateMailSender;
    private final PasswordEncoder passwordEncoder;
    private final TextUtils textUtils;
    private final DateUtils dateUtils;
    private final UserMapper userMapper;
    private final CloudinaryUpload cloudinaryUpload;

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public User findById(long id) {
        return userDAO.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    @Override
    public User save(User user) {
        return userDAO.save(user);
    }

    @Override
    public User findByEmailAddress(String emailAddress) {
        return userDAO.findByEmailAddress(emailAddress);
    }

    @Override
    public User findByRut(String rut) {
        return userDAO.findByRut(rut);
    }

    @Override
    public long countByRoleName(RoleName roleName) {
        return userDAO.countByRoles_Name(roleName);
    }

    @Override
    public LoginResponseDTO authenticateUser(LoginRequestDTO loginRequestDTO,
            AuthenticationManager authenticationManager) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.getRut(), loginRequestDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new LoginResponseDTO(jwtProvider.generateToken(authentication));
    }

    @Override
    public boolean checkEmailAvailability(String emailAddress) {
        return userDAO.existsByEmailAddress(emailAddress);
    }

    @Override
    public boolean checkRutAvailability(String rut) {
        return userDAO.existsByRut(rut);
    }

    @Override
    @SneakyThrows
    public User registerUser(RegisterRequestDTO registerRequestDTO) {

        if (checkEmailAvailability(registerRequestDTO.getEmailAddress())) {
            throw new EntityAlreadyExistsException("User with this email already exists");
        }

        if (checkRutAvailability(registerRequestDTO.getRut())) {
            throw new EntityAlreadyExistsException("User with this rut already exists");
        }

        Set<Role> roles = new HashSet<>();
        Role role = roleService.findById(registerRequestDTO.getRoleId()); 
        roles.add(role);

        User user = User.builder()
                .rut(registerRequestDTO.getRut())
                .firstname(registerRequestDTO.getFirstName())
                .lastname(registerRequestDTO.getLastName())
                .address(registerRequestDTO.getAddress())
                .emailAddress(registerRequestDTO.getEmailAddress())
                .password(passwordEncoder.encode(registerRequestDTO.getPassword()))
                .profileImage("")
                .gender(null)
                .city(null)
                .userSpecialties(role.getName() == RoleName.ROLE_DOCTOR ? null : registerRequestDTO.getUserSpecialties())
                .diseasesTreated(role.getName() == RoleName.ROLE_DOCTOR ? null : registerRequestDTO.getDiseaseTreateds())
                .roles(roles)
                .build();

        user = save(user);

        ActivationToken activationToken = ActivationToken.builder()
                .token(textUtils.generateSecureToken(20))
                .user(user)
                .build();

        activationToken = activationTokenService.save(activationToken);
        templateMailSender.sendAccountActivationMail(user, activationToken);

        return user;
    }

    @Override
    public LoginResponseDTO refreshToken(AuthenticationManager authenticationManager) {
        return null;
    }

    // WEB ADMIN METHODS

    @Override
    public String webSignIn(Model model, RedirectAttributes redirectAttributes, Principal principal, String error) {

        if (principal != null) {
            redirectAttributes.addFlashAttribute("info", "Ya tienes una sesión activa.");
            return "redirect:/web/users/dashboard";
        }

        if (error != null) {
            model.addAttribute("error", "Credenciales incorrectas.");
        }

        return "user/sign-in";
    }

    @Override
    public String dashboard(Model model) {

        List<Long> totalAppointmentsByWeekDay = new ArrayList<>();
        Date[] daysOfWeek = dateUtils.getDaysOfWeek(new Date(), Calendar.getInstance().getFirstDayOfWeek());

        for (Date date : daysOfWeek) {
            totalAppointmentsByWeekDay.add(
                    appointmentDAO.countByStatusAndCreatedAtBetween(
                            2, dateUtils.atStartOfDay(date), dateUtils.atEndOfDay(date)));
        }

        DashboardResponseDTO dashboardResponseDTO = DashboardResponseDTO.builder()
                .doctorsCount(countByRoleName(RoleName.ROLE_DOCTOR))
                .usersCount(countByRoleName(RoleName.ROLE_USER))
                .complainsCount(attentionRatingDAO.countByQualificationId(3))
                .appointmentsCount(appointmentDAO.countByStatus(2))
                .totalAppointmentsByWeekDay(totalAppointmentsByWeekDay)
                .attentionRatingFilterList(attentionRatingDAO.findTop5ByAttentionRatingQualification())
                .build();

        model.addAttribute("dashboardResponse", dashboardResponseDTO);

        return "dashboard/dashboard";
    }

    @Override
    public String index(Model model) {
        return "user/index";
    }

    @Override
    public String createUserRedirect(Model model) {

        model.addAttribute("userDTO", new UserDTO());
        model.addAttribute("specialties", specialtyService.findAll());
        model.addAttribute("diseasesTreated", diseaseTreatedService.findAll());
        model.addAttribute("roles", roleService.findAll());

        return "user/create";
    }

    @Override
    public String createUser(Model model, UserDTO userDTO) {

        userDTO.setProfileImage(cloudinaryUpload.uploadFile(userDTO.getProfileImageMultipartFile(), new ArrayList<>()));
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        User user = save(userMapper.toEntity(userDTO));

        return "redirect:/web/users/" + user.getId();
    }

    @Override
    public String findByIdUser(Model model, long id) {

        User user = findById(id);

        model.addAttribute("userDTO", userMapper.toDTO(user));
        model.addAttribute("specialties", specialtyService.findAll());
        model.addAttribute("diseasesTreated", diseaseTreatedService.findAll());
        model.addAttribute("roles", roleService.findAll());

        return "user/find";
    }

    @Override
    public String updateUser(Model model, UserDTO userDTO) {

        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User user = save(userMapper.toEntity(userDTO));

        return "redirect:/web/users/" + user.getId();
    }

    @Override
    public String deleteUser(Model model, long id) {

        User user = findById(id);
        user.setStatus(0);
        save(user);

        return "redirect:/web/users" + user.getId();
    }

    @Override
    public String activateAccount(String token) {

        ActivationToken activationToken = activationTokenService.findByToken(token);

        if (activationToken.getStatus() == 0) {
            return "response/error";
        }

        User user = activationToken.getUser();
        user.setStatus(1);
        save(user);

        activationTokenService.save(activationToken);

        return "user/account-activation-success";
    }

}
