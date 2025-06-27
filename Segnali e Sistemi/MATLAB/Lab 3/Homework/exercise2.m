load('convolution_in_matlab_insuline_data.mat');

tg = 0:T:420;
g = 0.76*exp(-0.14*tg) + 0.24*exp(-0.02*tg);

ty = tx(1) + tg(1):T:420;
y = T*conv(x, g);

figure
subplot(3, 1, 1)
plot(tx, x)
title("x")

subplot(3, 1, 2)
plot(tg, g)
title("g")

subplot(3, 1, 3)
plot(ty, y(1:421))
title("y=x*g")