t = -10:0.01:10;
b = 3;

s1 = tanh(t);
s2 = tanh(t - b);
s3 = tanh(t + b);

figure
plot(t, s1, t, s2, t, s3)
grid on
title('tanh')
xlabel('time [t]')
ylabel('signal [s]')
legend('tanh(t)', 'tanh(t - b)', 'tanh(t + b)')