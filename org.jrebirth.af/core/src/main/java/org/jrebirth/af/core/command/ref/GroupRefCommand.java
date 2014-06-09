package org.jrebirth.af.core.command.ref;

import org.jrebirth.af.core.command.AbstractMultiCommand;
import org.jrebirth.af.core.wave.Wave;
import org.jrebirth.af.core.wave.WaveBean;

public class GroupRefCommand extends AbstractMultiCommand<WaveBean> {


	public GroupRefCommand() {
		super(true);
		// Nothing to initialize
	}

	@Override
	protected void manageSubCommand() {
        //for (Object keyPart : getListKeyPart()) {

            //if (keyPart instanceof GroupRef) {
            	
            	//GroupRef groupRef = (GroupRef) keyPart;
				GroupRef groupRef = getKeyPart(GroupRef.class);
            	
				setSequential(groupRef.sequential());
				
				this.runIntoThread = groupRef.runType();
				this.runnablePriority = groupRef.priority();
				
				for(Ref ref  : groupRef.children()){
					if(ref instanceof GroupRef){
						addCommandKey(GroupRefCommand.class, ref);
					}else if(ref instanceof SingleRef){
						addCommandKey(RefCommand.class, ref);
					}else if(ref instanceof RealRef){
						addCommandKey(((RealRef) ref).commandKey());
					}
				}
				
                //break;
            //}
        //}
	}
	
	@Override
	protected void initCommand() {
		// Nothing to do
	}

}
