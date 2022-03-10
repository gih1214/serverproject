package site.metacoding.serverproject.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Hospital {

    // IDENTITY 전략은 DB에게 번호증가 전략을 위임하는 것!! - 알아서 디비에 맞게 찾아줌
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer no; // primary key

    private Integer id;

    private String addr; // 주소

    private Integer mgtStaDd; // 운영시작일자

    private String pcrPsblYn; // 구분코드

    private String ratPsblYn; // RAT가능여부

    private Integer recuClCd; // 요양종별코드

    private String sgguCdNm; // 시군구명

    private String sidoCdNm; // 시도명

    private String yadmNm; // 요양기관명

    private String ykihoEnc; // 암호화된요양기호

    private String xposWgs84; // 세계지구x좌표

    private String yposWgs84; // 세계지구y좌표

    private String xpos; // x좌표

    private String ypos; // y좌표
}
