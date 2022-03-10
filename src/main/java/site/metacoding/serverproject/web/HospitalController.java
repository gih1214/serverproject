package site.metacoding.serverproject.web;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import site.metacoding.serverproject.domain.Hospital;

@Controller
public class HospitalController {

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

        // 3. 리턴
        return "list";
    }

}
