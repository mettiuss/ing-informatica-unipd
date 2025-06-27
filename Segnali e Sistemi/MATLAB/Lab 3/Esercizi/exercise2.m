T = 0.01;

tx = -3:T:3;

x = rect(tx-1);

ty = tx(1) + tx(1):T:tx(end) + tx(end);
y = conv(x, x);

figure
subplot(2, 1, 1)
title("x")
plot(tx, x)
grid on

subplot(2, 1, 2)
title("x*x")
plot(ty, y)
grid on

function s = rect(t)
    s = (abs(t) < .5) + .5*(abs(t) == .5);
end