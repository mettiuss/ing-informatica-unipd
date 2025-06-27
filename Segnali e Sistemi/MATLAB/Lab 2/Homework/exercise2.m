%Plot the signal x(t) = tanh(t) together with its time-reversed and shifted
%versions yu(t) = x(u− t) with u an integer in the range [−9,10]. Make
%sure that each couple (x,yu) is plotted on a diﬀerent area of a 4 × 5 grid,
%and that the time span of each plot is [−10,10]. You will need to check
%how a for cycle works to solve the exercise.

t = -10:0.1:10;

s1 = tanh(t);

figure
for u = -9:10
    s2 = tanh(u - t);
    subplot(4, 5, u + 10);
    plot(t, s1, t, s2)
    grid on
    title(strcat('tanh(', num2str(u), '- t)'))
    xlabel('time [t]')
    ylabel('signal [s]')
end
