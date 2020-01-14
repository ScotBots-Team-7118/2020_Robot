package frc.robot;

/* Imports */

/**
 * Wrapper class for a BNO055 gyroscope typically used on the robot.
 */
public class Gyroscope implements RobotWrapper
{
    /**
    * An instance of the BNO055 class that uses euler vectors to read data.
    * This instance outputs vector directions in degrees for a range of all real numbers.
    */
    public static BNO055 imu = BNO055.getInstance(BNO055.opmode_t.OPERATION_MODE_IMUPLUS, BNO055.vector_type_t.VECTOR_EULER);

    /**
     * The angle offset value for the current instance of the gyroscope class.
     * This value is reset to the current imu heading value whenever the reset method is run.
     */
	private double angleOffset;
	
	/**
	 * The current heading value of the gyroscope in relation to the angle offset.
	 */
	private double currentHeading;
	
	/**
	 * The current raw heading value of the gyroscope.
	 */
	private double currentRawHeading;

	/**
	 * Constructs a new Gyroscope object.
	 */
	public Gyroscope()
	{
		reset();
	}

	/**
	 * Initialization process for a gyroscope object before enabling the robot.
	 * @param mode for which the gyroscope is being initialized.
	 */
	public void init(RunMode mode)
	{
		reset();	// The init function for all modes simply resets the gyroscope
	}

	/**
	 * Iterative program for a gyroscope object while the robot is enabled.
	 * @param mode for which the program is being run.
	 */
	public void update(RunMode mode)
	{
		updateRawHeading();	// The update function for all modes simply updates both heading values so they are ready to be fetched
		updateHeading();
	}

	/**
	 * @return the current heading in relation to the offset
	 */
	public double getHeading()
	{
		return currentHeading;
	}

	/**
	 * Update the current heading of the gyroscope in relation to the offset.
	 */
	public void updateHeading()
	{
		currentHeading = normalizeHeadingVal(getRawHeading() - angleOffset);
	}

	/**
	 * Resets the angle offset to the current heading.
	 */
	public void reset()
	{
		updateRawHeading();

		angleOffset = getRawHeading();	// Set angleOffset to the raw heading of the gyro.

        if (angleOffset == 360.0)	// Check if the angle offset is 360 degrees.
        {
			angleOffset = 0;    // If so, set angleOffset to 0. This accounts for a problem  previously seen on the field.
		}

		updateHeading();
	}

	/**
	 * Updates the current raw heading of the gyroscope.
	 */
	public void updateRawHeading()
	{
		currentRawHeading = normalizeHeadingVal(imu.getVector()[0]);
	}

	/**
	 * Gets the normalized heading of the gyroscope without taking the angle offset into account.
	 * @return
	 */
	public double getRawHeading()
	{
		return currentRawHeading;
	}
	
	/**
	 * Returns the angle offset.
	 * @return
	 */
	public double getOffset()
	{
		return angleOffset;
	}

	/**
	 * Normalizes a heading value to the range of (-180, 180] degrees.
	 * @return
	 */
	private double normalizeHeadingVal(double heading)
	{
        heading = heading % 360;    // Set the gyroscope heading value to the remainder of its current value divided by 360.
        
        if (heading > 180.0)    // Check if the remainder of the given heading and 360 is greater than 180.
        {
			heading = heading - 360;    // If so, set the heading to a negative value greater than -180.
		}

        else if (heading <= -180.0) // Otherwise, check if the opposite case is true.
        {
			heading = heading + 360.0;  // If so, set the heading to a positive number less than 180.
		}

		return heading; // Return the new, normalized heading.
	}
}