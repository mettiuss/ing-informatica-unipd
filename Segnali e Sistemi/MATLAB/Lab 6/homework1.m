load("homework1.mat")

x = x - mean(x);

y = [x, zeros(1, 2*length(x))];

X = fftshift(fft(y)*T);

N = length(y);
w = (-round((N-1)/2):round(N/2)-1) *2*pi/(N*T);

[maxval,pos] = max(abs(X));

w_0 = abs(w(pos));

T_p = (2*pi)/w_0

figure
subplot(2, 1, 1)
plot(t, x)
hold on
grid on
xlabel("time")
ylabel("x(t)")
title("ekg signal")



subplot(2, 1, 2)
semilogy(w, abs(X))
grid on
xlabel("\omega")
ylabel("X(\omega)")
title("fourier transform")