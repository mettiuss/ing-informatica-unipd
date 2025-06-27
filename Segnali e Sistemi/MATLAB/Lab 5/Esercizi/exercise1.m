T = 0.01;
t = -1:T:10;

x = 2*exp(-t).*cos(2*pi*t).*((t > 0) + .5*(t == 0));

N = length(x);

w = (0:N-1)*(2*pi)*(T*N);
X = T*fft(x);

X_1 = fftshift(T*fft(x));

w_1 = (-round((N-1)/2):round(N/2)-1) *2*pi/(N*T);

X_ref = 1./(1+1i*(w_1 - 2*pi)) + 1./(1+1i*(w_1 + 2*pi));

figure
subplot(2, 3, 1)
plot (t , x)
grid on
xlabel('t')
ylabel('x(t)')
title('signal')

subplot(2, 3, 2)
semilogy(w, abs(X))
grid on
xlabel('\omega')
ylabel('X(j\omega)')
title("Fourier Transform")

subplot(2, 3, 3)
semilogy(w_1, abs(X_1), w_1, abs(X_ref), 'r')
grid on
xlabel('\omega')
ylabel('X(j\omega)')
title("Fourier Transform Shifted")
legend('matlab', 'analytical')

subplot(2, 3, 5)
plot(t, ifft(X)/T)
grid on
xlabel('t')
ylabel('x(t)')
title("Fourier Anti-Transform")

subplot(2, 3, 6)
plot(t, real(ifft(fftshift(X_1))/T))
grid on
xlabel('t')
ylabel('x(t)')
title("Shifted Fourier Anti-Transform")
