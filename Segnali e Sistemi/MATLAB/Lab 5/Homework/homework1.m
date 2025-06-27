T = 0.01;

t = -10:T:10;

x = triang(t);

N = length(x);

X = ifftshift(T*fft(x));

w = (-round((N-1)/2):round(N/2)-1) *2*pi/(N*T);

X_1 = sinc(w/(2*pi)).^2;

figure
subplot(2, 1, 1)
plot(t, x)
grid on
xlabel('t')
ylabel('triang(t)')
title('signal')

subplot(2, 1, 2)
semilogy(w, abs(X), w, abs(X_1))
legend('matlab', 'analytical')
grid on
xlabel('\omega')
ylabel('X(j\omega)')
title('fourier transform')
xlim([0, 315])

function s = triang(t)
    s = (t >= -1).*(t < 0).*(t + 1) + (t <= 1).*(t>=0).*(-t+1);
end

function s = sinc(t)
    s = sin(pi*t)./(pi*t);
end