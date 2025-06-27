t = -1:0.01:8;
s = 100*exp((-1 + 1i*2*pi)*t).*(t >= 0);

figure
subplot(2, 2, 1)
plot(t, real(s))
grid on
title('Real')

subplot(2, 2, 2)
plot(t, imag(s))
grid on
title('Imaginary')

subplot(2, 2, 3)
plot(t, abs(s))
grid on
title('Absolute Value')

subplot(2, 2, 4)
plot(t, angle(s))
grid on
title('Angle')