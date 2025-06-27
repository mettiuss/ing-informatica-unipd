T = 0.01;

tx = -20:T:20;

x = sinc(tx);

ty = tx(1)*2:T:tx(end)*2;
y = T*conv(x, x);

figure
subplot(2, 1, 1)
title("x")
plot(tx, x)
grid on

subplot(2, 1, 2)
title("x*x")
plot(ty, y)
grid on
