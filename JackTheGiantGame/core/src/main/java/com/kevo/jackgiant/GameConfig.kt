@file:JvmName("GameConfig")

package com.kevo.jackgiant

/**
 * The world gravity vector.
 */
const val WORLD_GRAVITY = 9.8f

/**
 * Default vertical scrolling speed for the game camera.
 */
const val CAMERA_DEFAULT_SPEED = 0.5f

/**
 * The world force vector, usually in Newtons (N).
 */
const val PLAYER_MOVEMENT_FORCE = 10f

/**
 * The world impulse vector, usually in N-seconds or kg-m/s.
 */
const val PLAYER_JUMP_FORCE = 8f

/**
 * The density, usually in kg/m^2.
 */
const val PLAYER_DENSITY = 2f

/**
 * The friction coefficient, usually in the range [0,1].
 */
const val PLAYER_FRICTION = 0.5f

/**
 * The density, usually in kg/m^2.
 */
const val CLOUD_DENSITY = 10f

/**
 * The friction coefficient, usually in the range [0,1].
 */
const val CLOUD_FRICTION = 0.1f
