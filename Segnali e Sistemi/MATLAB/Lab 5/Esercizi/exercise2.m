load('exercise2.mat')

N = length(x);

X = ifftshift(T*fft(x));

w = (-round((N-1)/2):round(N/2)-1) *2*pi/(N*T);

figure
subplot(2, 1, 1)
plot(t, x)
grid on
xlabel('t')
ylabel('x(t)')
title('signal')

subplot(2, 1, 2)
semilogy(w, abs(X))
grid on
xlabel('\omega')
ylabel('X(j\omega)')
title('fourier transform')
xlim([0 31.5])
