load("homework2.mat")

N = length(x);

X = ifftshift(T*fft(x));

w = (-round((N-1)/2):round(N/2)-1) *2*pi/(N*T);

G = ifftshift(T*fft(g));

Y = X.*G;

y = ifft(ifftshift(Y)/T);

figure
subplot(4, 2, 1)
plot(t, x)
grid on
xlabel('t')
ylabel('x(t)')
title('signal')

subplot(4, 2, 2)
semilogy(w, abs(X))
grid on
xlabel('\omega')
ylabel('X(j\omega)')
title('fourier transform')

subplot(4, 2, 3)
plot(t, g)
grid on
xlabel('t')
ylabel('g(t)')
title('impulse response')

subplot(4, 2, 4)
semilogy(w, abs(G))
grid on
xlabel('\omega')
ylabel('G(j\omega)')
title('fourier transform')

subplot(4, 1, 3)
plot(t, abs(y))
grid on
xlabel('t')
ylabel('y(t)')
title('plasma concentration')

subplot(4, 1, 4)
semilogy(w, abs(Y))
grid on
xlabel('\omega')
ylabel('Y(j\omega)')
title('fourier transform')
