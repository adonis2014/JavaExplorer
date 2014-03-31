package name.chenyuelin.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.zip.Adler32;

import name.chenyuelin.validator.FileUploadControllerValidator;

import org.aspectj.util.FileUtil;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("upload")
@SessionAttributes("person")
public class FileUploadController {

	@InitBinder
    protected void initBinder(WebDataBinder binder,ServletWebRequest nativeWebRequest){
	    Object target=binder.getTarget();
	    binder.setValidator(new FileUploadControllerValidator());
//	    nativeWebRequest.getNativeRequest();
	    System.out.println(nativeWebRequest.getContextPath());
	    System.out.println(nativeWebRequest.getNativeRequest());
	    if(target!=null){
//	    	Validator validator=validatorMap.get(target.getClass());
//	    	if(validator!=null){
//	    		binder.setValidator(validator);
//	    	}
	    }
    }
	
	@RequestMapping(value="one",method = RequestMethod.POST)
	@ResponseBody
	public void uploadOne(@RequestParam("name") String name,
	        @RequestParam("files") MultipartFile[] files) throws Exception{
		System.out.println(name);
		for(MultipartFile multipartFile:files){
			StringBuilder outputInfo=new StringBuilder();
			outputInfo.append("FileName:").append(multipartFile.getName()).append("\t");
			outputInfo.append("ContentType:").append(multipartFile.getContentType()).append("\t");
			Adler32 adler32=new Adler32();
			adler32.update(multipartFile.getBytes());
			outputInfo.append("Adler32:").append(Long.toHexString(adler32.getValue()));
			System.out.println(outputInfo.toString());
		}
	}
	
	@RequestMapping(value="download1/{picName}",method = RequestMethod.GET,produces={"application/octet-stream"})
	@ResponseBody
	public byte[] download1(@PathVariable("picName")String picName) throws Exception{
		return FileUtil.readAsByteArray(new File("E:/pic/[Be]2013.05.03 No.816 Susan[73P192M]/"+picName));
	}
	
	@RequestMapping(value="download2/{picName}",method = RequestMethod.GET)
	public void download2(@PathVariable("picName")String picName,OutputStream output) throws Exception{
		InputStream in=new FileInputStream(new File("E:/pic/[Be]2013.05.03 No.816 Susan[73P192M]/"+picName));
		FileUtil.copyStream(in, output);
	}
	
	@RequestMapping(value="download3/{picName}",method = RequestMethod.GET)
	public HttpEntity<byte[]> download3(@PathVariable("picName")String picName) throws Exception{
		byte[] data=FileUtil.readAsByteArray(new File("E:/pic/[Be]2013.05.03 No.816 Susan[73P192M]/"+picName));
		HttpHeaders httpHeaders=new HttpHeaders();
		httpHeaders.setContentLength(data.length);
		httpHeaders.setContentType(MediaType.valueOf("application/octet-stream"));
		httpHeaders.add("Content-Disposition", "attachment;filename*="+URLEncoder.encode("√¿≈Æ.jpg","utf-8"));
		HttpEntity<byte[]> httpEntity=new ResponseEntity<byte[]>(data,httpHeaders,HttpStatus.OK);
		return httpEntity;
	}
}
