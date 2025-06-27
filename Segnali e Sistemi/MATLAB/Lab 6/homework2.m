load("homework2.mat")

x = x - mean(x);

x = [x, zeros(1, 2*length(x))];

N = length(x);

t = (0: N-1)*T;

X = fftshift(fft(x)*T);
w = (-round((N-1)/2):round(N/2)-1) *2*pi/(N*T);

Y = 1 - rect(w);

Z = X.*Y;

z = ifft(ifftshift(Z)/T);

figure
subplot(2, 2, 1)
plot(t, x)
hold on
grid on
xlabel("time")
ylabel("x(t)")
title("ekg signal distorted")
axis([0 20 ylim])


subplot(2, 2, 3)
semilogy(w, abs(X))
grid on
xlabel("\omega")
ylabel("X(\omega)")
title("fourier transform distorted")
axis([0 20 1e1 1e4])

subplot(2, 2, 4)
semilogy(w, abs(Z))
grid on
xlabel("\omega")
ylabel("Z(\omega)")
title("fourier transform corrected")
axis([0 20 1e1 1e4])


subplot(2, 2, 2)
plot(t, z)
grid on
xlabel("time")
ylabel("z(t)")
title("ekg signal corrected")
axis([0 20 ylim])

function X = rect(w)
    X = 1.*(w < pi) + 1.*(w > -pi) -1  + 0.5.*(w == pi) + 0.5.*(w == -pi);
end
