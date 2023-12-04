package xyz.ipiepiepie.car;

/**
 * Represents car for {@link Main cars meeting time exercise}
 */
public class Car {
    private final int velocity;
    private final int acceleration;
    
    /**
     * Constructor for new instances
     *
     * @param velocity car velocity
     * @param acceleration car acceleration
     */
    public Car(int velocity, int acceleration) {
        this.velocity = velocity;
        this.acceleration = acceleration;
    }
    
    /// VELOCITY ///
    
    /**
     * Get current car velocity
     *
     * @return velocity value
     */
    public int getVelocity() {
        return velocity;
    }
    
    /// ACCELERATION ///
    
    /**
     * Get current car acceleration
     *
     * @return acceleration value
     */
    public int getAcceleration() {
        return acceleration;
    }
    
}
