package domain;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// ** DTO
// => private 멤버변수
// => getter/setter
// => toString

@Getter
@Setter
@ToString
public class MemberDTO {
	private String id;
	private String password;
	private String name;
	private int age;
	private int jno;
	private String info;
	private double point;
	private String birthday;
	private String rid;
	private String uploadfile;
	private MultipartFile uploadfilef;
}
