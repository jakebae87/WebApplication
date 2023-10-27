package domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// ** DTO
// => private 멤버변수
// => getter/setter
// => toString

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {
	private int seq;
	private String id;
	private String title;
	private String content;
	private String regdate;
	private int cnt;

}
