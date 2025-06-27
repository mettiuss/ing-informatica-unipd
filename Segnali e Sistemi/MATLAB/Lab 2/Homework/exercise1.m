%Plot the signal s(t) = tanh(t) together with its time-shifted and scaled
%versions tanh(at), tanh(t/a), tanh(at− b), tanh(at+ b), tanh((t− b)/a),
%tanh((t+b)/a) in the same plot in the time range [−10,10], by using a= 2
%and b= 6.

a = 2;
b = 3;
t = -8:0.1:8;

figure;
plot(t, tanh(a*t), t, tanh(t/a), ...
    t, tanh(a*t - b), t, tanh(a*t + b),...
    t, tanh((t-b)/a), t, tanh((t+b)/a))
grid on
title('tanh')
xlabel('time [t]')
ylabel('signal [s]')
legend('tanh(a*t)', 'tanh(t/a)', ...
    'tanh(a*t - b)', 'tanh(a*t + b)', ...
    'tanh((t-b)/a)', 'tanh((t+b)/a)')