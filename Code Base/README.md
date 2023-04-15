# PoolGame

**Note 1:** The friction value in the config file is used as a multiplier in the
implementation. As the friction approaches 0, the friction decreases. As friction
approach 1, the friction increases. A high value of friction will make it 
impossible for a ball to move. Range of valid friction is: `0 < friction < 1`.

**Note 2:** While the forces applied to the cue ball is variable based on the
length of the line shown when dragging from the cue ball, there is a maximum cap
on the force.

**Note 3:** The center of the ball needs to be in the pocket for the code to 
consider it as "in the pocket" instead of its rectangular bound just intersecting
with the pocket's rectangular bound.

## Commands

* Run: `gradle run` to load default config from resources folder or 
`gradle run --args="'insert_config_file_path'"` to load custom config.

* Generate documentation:`gradle javadoc`