package domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class JoDTO extends MemberDTO{
	private int jno;
	private String jname;
	private String id;
//	private String cname;
	private String project;
	private String slogan;
	
}
