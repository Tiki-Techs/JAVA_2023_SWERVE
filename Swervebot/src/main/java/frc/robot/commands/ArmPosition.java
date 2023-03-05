package frc.robot.commands;

public interface ArmPosition {
    /**
     * 
     * @return true when it has successfully moved to the hold position
     */
    public boolean moveToHold();
    
    /**
     * 
     * @return true when it has successfully moved to its target position
     */
    public boolean moveToPosition();
}