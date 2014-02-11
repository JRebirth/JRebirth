package org.jrebirth.form.test.bean;

import javafx.scene.Node;

import org.jrebirth.core.command.Command;
import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.exception.WaveException;
import org.jrebirth.core.facade.LocalFacade;
import org.jrebirth.core.key.UniqueKey;
import org.jrebirth.core.service.Service;
import org.jrebirth.core.ui.InnerModels;
import org.jrebirth.core.ui.Model;
import org.jrebirth.core.ui.View;
import org.jrebirth.core.wave.Wave;
import org.jrebirth.core.wave.WaveData;
import org.jrebirth.core.wave.WaveType;

public class FormModel implements Model {

	@Override
	public void ready() throws CoreException {
	}

	@Override
	public LocalFacade<Model> getLocalFacade() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLocalFacade(LocalFacade<Model> localFacade) {
		// TODO Auto-generated method stub

	}

	@Override
	public UniqueKey getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setKey(UniqueKey key) {
		// TODO Auto-generated method stub

	}

	@Override
	public void release() {
		// TODO Auto-generated method stub

	}

	@Override
	public void listen(WaveType waveType) {
		// TODO Auto-generated method stub

	}

	@Override
	public void listen(WaveType waveType, boolean checkWaveContract) {
		// TODO Auto-generated method stub

	}

	@Override
	public void registerCallback(WaveType callType, WaveType responseType) {
		// TODO Auto-generated method stub

	}

	@Override
	public void registerCallback(WaveType callType, WaveType responseType,
			boolean checkWaveContract) {
		// TODO Auto-generated method stub

	}

	@Override
	public void unlisten(WaveType waveType) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendWave(Wave wave) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendWave(WaveType waveType, WaveData<?>... waveData) {
		// TODO Auto-generated method stub

	}

	@Override
	public void callCommand(Class<? extends Command> commandClass,
			WaveData<?>... data) {
		// TODO Auto-generated method stub

	}

	@Override
	public void returnData(Class<? extends Service> serviceClass,
			WaveType waveType, WaveData<?>... data) {
		// TODO Auto-generated method stub

	}

	@Override
	public void attachUi(Class<? extends Model> modelClass, WaveData<?>... data) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handle(Wave wave) throws WaveException {
		// TODO Auto-generated method stub

	}

	@Override
	public View<?, ?, ?> getView() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node getRootNode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Model getRootModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setRootModel(Model rootModel) {
		// TODO Auto-generated method stub

	}

	@Override
	public Model getInnerModel(InnerModels innerModel,
			UniqueKey... innerModelKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void showView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hideView() {
		// TODO Auto-generated method stub

	}

}
