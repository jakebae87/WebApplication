package downloadTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

public class DownloadView extends AbstractView {

	public DownloadView() {
		// 브라우저가 헤더에 담긴 아래 정보를 보고 다운로드 준비를 한다.
		setContentType("application/download; utf-8");
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// MemberController에서 return된 실제 이미지가 위치한 정보를 가져온다.
		File file = (File) model.get("downloadFile");

		response.setContentType(getContentType());
		response.setContentLength((int) file.length());

		String userAgent = request.getHeader("User-Agent");
		boolean ie = userAgent.indexOf("MSIE") > -1;
		String fileName = null;

		if (ie) {
			fileName = URLEncoder.encode(file.getName(), "utf-8");
		} else {
			fileName = new String(file.getName().getBytes("utf-8"));
		}

		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		OutputStream out = response.getOutputStream();
		FileInputStream fis = null;

		try {
			fis = new FileInputStream(file);
			FileCopyUtils.copy(fis, out);
			// => 파일 및 스트림 복사를 위한 간단한 유틸리티 메소드의 집합체
			// 지정한 입력 File 내용을 지정한 출력 File에 복사
		} catch (Exception e) {
			System.out.println("** DownloadView Exception => " + e.toString());
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (Exception e) {
				}
			}
		} // try end;

		out.flush();

	}
}
