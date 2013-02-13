/**
 * 
 */
package name.chenyuelin.command;

import java.util.Collection;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author U1
 * @version 1.0 2013-2-7
 */
public class PersonUploadInformation {
	private int id;
	private String name;
	private Collection<MultipartFile> uploadFile;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<MultipartFile> getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(Collection<MultipartFile> uploadFile) {
		this.uploadFile = uploadFile;
	}
	
	public String toString(){
		StringBuilder sb=new StringBuilder(50);
		sb.append("{id:").append(id).append(", ");
		sb.append("name:").append(name).append(", ");
		sb.append("uploadFiles:").append(uploadFile).append("}");
		return sb.toString();
	}
}
