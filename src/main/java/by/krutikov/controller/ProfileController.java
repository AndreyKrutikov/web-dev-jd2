package by.krutikov.controller;

import by.krutikov.controller.requests.ProfileFindOffsetLimitRequest;
import by.krutikov.entity.Account;
import by.krutikov.entity.Experience;
import by.krutikov.entity.InstrumentType;
import by.krutikov.entity.Media;
import by.krutikov.entity.Profile;
import by.krutikov.entity.Role;
import by.krutikov.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profiles")
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping("/test")
    public ResponseEntity<Object> test() {
        Profile profile1 = Profile
                .builder()
                .id(1L)
                .account(null)
                .media(Media.builder()
                        .id(2L)
                        .photoUrl("aDSASD_URL")
                        .demoUrl("ssffaefc")
                        .build())
                .isVisible(true)
                .dateCreated(new Timestamp(new Date().getTime()))
                .dateModified(new Timestamp(new Date().getTime()))
                .description("descijqfjqicic iueidqweiufdiufnipf")
                .latitude(8.09898)
                .longitude(0.544390)
                .displayedName("Profile sdasdasmkml")
                .experience(Experience.AMATEUR)
                .instrument(InstrumentType.BASS)
                .phoneNumber("5678201")
                .build();

        Profile profile2 = Profile
                .builder()
                .id(1L)
                .account(null)
                .media(Media.builder()
                        .id(1L)
                        .photoUrl("aDSASD_URL")
                        .demoUrl("adsfpok")
                        .build())
                .isVisible(true)
                .dateCreated(new Timestamp(new Date().getTime()))
                .dateModified(new Timestamp(new Date().getTime()))
                .description("descijqfjqicic iueidqweiufdiufnipf")
                .latitude(-54.578)
                .longitude(11.3)
                .displayedName("Profile sdasdasmkml")
                .experience(Experience.PRO)
                .instrument(InstrumentType.DRUMS)
                .phoneNumber("0099000")
                .build();

        Profile profile3 = Profile
                .builder()
                .id(1L)
                .account(null)
                .media(null)
                .isVisible(true)
                .dateCreated(new Timestamp(new Date().getTime()))
                .dateModified(new Timestamp(new Date().getTime()))
                .description("desciqicic iueidqweiufdiufnipf")
                .latitude(8.09898)
                .longitude(0.544390)
                .displayedName("Profile sdasdasmkml")
                .experience(Experience.BEGINNER)
                .instrument(InstrumentType.VOCALS)
                .phoneNumber("56998201")
                .build();

        Profile profile4 = Profile
                .builder()
                .id(1L)
                .account(null)
                .media(null)
                .isVisible(true)
                .dateCreated(new Timestamp(new Date().getTime()))
                .dateModified(new Timestamp(new Date().getTime()))
                .description("descijqfjqicic iueidqweiufdiufnipf")
                .latitude(8.09898)
                .longitude(90.75)
                .displayedName("fffdsafasdf")
                .experience(Experience.AMATEUR)
                .instrument(InstrumentType.BASS)
                .phoneNumber("5678201")
                .build();

        List<Profile> profiles = new ArrayList<>();
        profiles.add(profile1);
        profiles.add(profile2);
        profiles.add(profile3);
        profiles.add(profile4);

        return new ResponseEntity<>(Collections.singletonMap("testing: ", profiles), HttpStatus.OK);
    }


    @GetMapping
    //@ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> findAllProfiles() {
        return new ResponseEntity<>(Collections.singletonMap("all profiles: ", profileService.findAll()), HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<Object> findAllOffsetLimit(@ModelAttribute ProfileFindOffsetLimitRequest findLimitOffsetRequest) {
        int verifiedOffset = Integer.parseInt(findLimitOffsetRequest.getOffset());
        int verifiedLimit = Integer.parseInt(findLimitOffsetRequest.getLimit());

        List<Profile> profiles = profileService.findAll(verifiedOffset, verifiedLimit);
        Map<String, List<Profile>> model = Collections.singletonMap("result", profiles);

        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findAccountById(@PathVariable String id) {
        long idVerified = Long.parseLong(id);

        return new ResponseEntity<>(Collections.singletonMap("result", profileService.findById(idVerified)), HttpStatus.OK);
    }

//    @GetMapping("/delete/{id}")
//    public ResponseEntity<Object> deleteAccountById(@PathVariable String id) {
//        long idVerified = Long.parseLong(id);
//
//        profileService.delete(idVerified);
//        return new ResponseEntity<>(Collections.singletonMap("result", profileService.findAll()), HttpStatus.OK);
//
//    }

//    @PostMapping
//    public ResponseEntity<Object> createAccount(@RequestBody AccountCreateRequest createRequest) {
//        Profile profile = new Profile();
//        profile.setLogin(createRequest.getLogin());
//        profile.setPassword(createRequest.getPassword());
//        profile.setEmail(createRequest.getEmail());
//
//        profileService.create(account);
//
//        List<Profile> accounts = profileService.findAll();
//
//        Map<String, Object> model = new HashMap<>();
//        model.put("account created", account.getLogin());
//        model.put("accounts", accounts);
//
//        return new ResponseEntity<>(model, HttpStatus.CREATED);}


//    @PutMapping("/update/{id}")
//    public ResponseEntity<Object> updateAccount(@RequestBody AccountUpdateRequest updateRequest,
//                                                @PathVariable String id) {
//        long idVerified = Long.parseLong(id);
//
//        Profile profile = profileService.findById(idVerified);
//        profile.setLogin(updateRequest.getLogin());
//        profile.setPassword(updateRequest.getPassword());
//        profile.setEmail(updateRequest.getEmail());
//
//        profileService.update(profile);
//
//        List<Profile> accounts = profileService.findAll();
//
//        Map<String, Object> model = new HashMap<>();
//        model.put("updated account", Profile.getLogin());
//        model.put("accounts", accounts);
//
//        return new ResponseEntity<>(model, HttpStatus.CREATED);

//    }
}
