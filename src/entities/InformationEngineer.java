package entities;

public class InformationEngineer extends ChangeInitiator {

	private InfoSystem managedSystem;
	private Position position;

	public InfoSystem getManagedSystem() {
		return this.managedSystem;
	}

	/**
	 * 
	 * @param managedSystem
	 */
	public void setManagedSystem(InfoSystem managedSystem) {
		this.managedSystem = managedSystem;
	}

	public InformationEngineerPhasePosition.PhasePosition getPosition() {
		// TODO - implement InformationEngineer.getPosition
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param position
	 */
	public void setPosition(InformationEngineerPhasePosition.PhasePosition position) {
		// TODO - implement InformationEngineer.setPosition
		throw new UnsupportedOperationException();
	}

}