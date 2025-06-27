Tp = 5; % period
d = .5; % duty cycle

t = 0:0.001:Tp/2;
s = square_wave(t, Tp, d);

figure
plot(t, s);
hold on

plot(t, real(fourier_series_reconstruct(t, Tp, d, 100)))
plot(t, real(fourier_series_reconstruct_2(t, Tp, d, 100, 1000)))
axis ([1 1.3 .85 1.15])
grid on
legend ( ' signal ' , ' true coefs ', ' M = 1000 ')
title ( 'approx truncated Fourier series ')


function tfs = fourier_series_reconstruct(t, Tp, d, N)
    tfs = zeros ( size ( t ) );
    for k = -N:N
       ak = (d*(sinc(k*d)));
       tfs = tfs + ak* exp(1i*k*((2*pi)/Tp)*t);
    end
end

function tfs = fourier_series_reconstruct_2(t, Tp, d, N, M)
    bk = 0;
    tfs = zeros ( size ( t ) );
    for k = -N:N
       for n = 0:(M-1)
           bk = bk + square_wave(n*(Tp / M), Tp, d)* exp(-1i*k*((2*pi)/Tp)*n*(Tp / M));
       end
       bk = (1/M)*bk;
       tfs = tfs + bk* exp(1i*k*((2*pi)/Tp)*t);
    end
end

function s = square_wave (t , Tp , d )
    t1 = mod ( t / Tp ,1);
    s = rect ( t1 / d ) + rect (( t1 -1) / d );
end

function s = rect ( t )
    s = ( abs ( t ) <.5) +.5*( abs ( t ) ==.5);
end
