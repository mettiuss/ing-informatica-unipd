
x = [-1 3 -5 2];

y = [1 2 -1];

figure
subplot(2, 2, 1)
stem(-1:1:2, x)
title("x")
grid on

subplot(2, 2, 2)
stem(0:1:2, y)
title("y")
grid on

subplot(2, 1, 2)
stem(-1:1:4, conv(x,y))
title("x*y")
grid on