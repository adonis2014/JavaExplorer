package name.chenyuelin.webapp.transformer;

import name.chenyuelin.entity.test.SsRole;
import name.chenyuelin.webapp.command.SsRoleCommand;

public final class Transformer {
	private Transformer(){}
	
	public static SsRole toSsRoleEntity(SsRoleCommand ssRoleCommand){
		if(ssRoleCommand==null){
			return null;
		}
		SsRole ssRole=new SsRole();
		ssRole.setId(ssRoleCommand.getId());
		ssRole.setName(ssRoleCommand.getName());
		ssRole.setEnabled(ssRoleCommand.getEnabled());
		ssRole.setDescription(ssRoleCommand.getDescription());
		return ssRole;
	}
}
