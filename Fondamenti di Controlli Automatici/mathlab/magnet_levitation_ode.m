function [dxdt] = magnet_levitation_ode(z, z_dot, input_current, params)
    % magnet_levitation_ode - Ordinary Differential Equation (ODE) for magnet levitation system
    %   This function defines the ODEs governing the behavior of a simple approximated magnet levitation system.
    %
    % Syntax: 
    %   dxdt = magnet_levitation_ode(z, z_dot, input_current, params)
    %
    % Inputs:
    %   z - Position of the magnet
    %   z_dot - Velocity of the magnet
    %   input_current - Current supplied to the system
    %   params - Structure containing system parameters
    %
    % Outputs:
    %   dxdt - Time derivatives of magnet position and velocity
    %
    % Parameters:
    %   params.m - Mass of the magnet [kg]
    %   params.b_m - Magnetic damping coefficient [N*m/A]
    %   params.b_s - Spring damping coefficient [N*m/A]
    %   params.g - Acceleration due to gravity [m/s^2]
    %   params.d - Damping coefficient [N*s/m]
    %   params.i_max - Maximum current limit [A]

    % Ensure input current stays within limits
    %i = max(min(input_current, params.i_max), -params.i_max);
    i = input_current;

    % Compute acceleration
    acceleration = (params.b_m / z + params.b_s * i / z - params.d * z_dot) / params.m - params.g;

    % Pack derivatives into a column vector
    dxdt = [z_dot; acceleration];

end % function