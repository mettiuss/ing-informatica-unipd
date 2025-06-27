T_p = 5;
T = .01;
d = .5;

t = 0:T:T_p/2;
s = square_wave(t, T_p, d);

figure
plot(t, s)
hold on
grid on

for N = [5, 10, 20, 50, 100, 200]
    s_N = fourier_series_reconstruct(t, T_p, N, d);
    plot(t, real(s_N))
end

function s = square_wave(t, T_p, d)
    t1 = mod(t/T_p, 1);
    s = rect(t1/d) + rect((t1 - 1)/d);
end

function s = rect(t)
    s = 1*(abs(t) < 0.5) + 0.5*(t == 0.5) + 0.5*(t == -0.5);
end

function s = fourier_series_reconstruct(t, T_p, N, d)
    s = zeros(size(t));
    for k = -N:N
        a_k = d*sinc(k*d);
        s = s + a_k * exp(1i*k*2*pi*t/T_p);
    end
end