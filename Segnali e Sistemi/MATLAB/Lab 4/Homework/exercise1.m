Tp = 5; % period
d = .3; % duty cycle

t = 0:0.001:Tp/2;
s = triang_wave(t, Tp, d);

figure
plot(t, s);
hold on
for N = [5 ,10 ,20 ,50 ,100 ,200]
    plot(t, real(fourier_series_reconstruct(t, Tp, d, N)))
end
grid on
legend ( 'N =\infty ' , '5 ' , ' 10 ' , ' 20 ' , ' 50 ' , ' 100 ' , ' 200 ')
title ( ' truncated Fourier series ')


function tfs = fourier_series_reconstruct(t, Tp, d, N)
    tfs = zeros ( size ( t ) );
    for k = -N:N
       ak = (d*(sinc(k*d)^2));
       tfs = tfs + ak* exp(1i*k*((2*pi)/Tp)*t);
    end
end

function s = triang_wave (t , Tp , d )
    t1 = mod ( t / Tp ,1);
    s = triang ( t1 / d ) + triang (( t1 -1) / d );
end

function s = triang ( t )
    s = ( abs ( t ) <1) .*(1 - abs ( t ) );
end
