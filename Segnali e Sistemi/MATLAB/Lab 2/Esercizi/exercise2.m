t = -1:0.01:10;
s = (t>=0).*exp(-t);

figure
plot(t, s)
grid on
title("e^-t")
xlabel('time [t]')
ylabel('signal [s]')
