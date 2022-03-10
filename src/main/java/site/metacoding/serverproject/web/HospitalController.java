package site.metacoding.serverproject.web;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import site.metacoding.serverproject.domain.Hospital;
import site.metacoding.serverproject.domain.HospitalRepository;

@RequiredArgsConstructor // final이 붙은 애들에 대한 생성자 만들어줌
@Controller
public class HospitalController {

    private final HospitalRepository hospitalRepository;

    // 메인 페이지
    @GetMapping("/")
    public String home() {
        return "/download";
    }

    // 상세 페이지
    @GetMapping("/download")
    public String download(Model model) {

        // 1. URI로 다운로드
        RestTemplate rt = new RestTemplate(); // API 통신해서 가져올 수 있도록 도와줌
        // getForObject() 메서드는 오브젝트를 바로 받지 못하기 때문에 배열로 받은 후 리스트로 변환시켜야 한다.
        // 배열로 반환받기 -> 클래스명[] 변수a = rt.getForObject("url", 클래스명[].class);
        // 배열을 리스트로 변환하기 -> List<클래스명> 변수b = Arrays.asList(변수a);
        Hospital[] response = rt.getForObject("http://3.38.254.72:5000/api/hospital?sidoCdNm=부산&sgguCdNm=부산사하구",
                Hospital[].class);
        List<Hospital> list = Arrays.asList(response);
        // System.out.println(list.get(0));

        // 2. DB에 saveAll() + model에 담기
        // JPA Repository의 saveAll() 메서드를 활용하여 Insert문을 하나로 묶어 한번에 처리하기
        hospitalRepository.saveAll(list);
        // System.out.println(hospitalRepository.saveAll(list));

        // saveAll로 DB에 Insert 했다. 이제 model에 담아보자.
        model.addAttribute("lists", hospitalRepository.findAll());
        // System.out.println(model.addAttribute("lists",
        // hospitalRepository.findAll()));

        // 3. 리턴
        return "list";
    }

}
