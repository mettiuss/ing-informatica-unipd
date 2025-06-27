Tp = 5; % period
d = .5; % duty cycle

t = 0:0.001:Tp/2;
s = signal(t);

figure
plot(t, s);
hold on

plot(t, real(fourier_series_reconstruct_2(t, Tp, d, 100, 1000)))
plot(t, real(fourier_series_reconstruct_2(t, Tp, d, 100, 10000)))


grid on
legend ( ' signal ' , ' M = 1000 ', ' M = 10000 ')
title ( 'approx truncated Fourier series ')

function tfs = fourier_series_reconstruct_2(t, Tp, d, N, M)
    bk = 0;
    tfs = zeros ( size ( t ) );
    for k = -N:N
       for n = 0:(M-1)
           bk = bk + signal(n*(Tp / M))* exp(-1i*k*((2*pi)/Tp)*n*(Tp / M));
       end
       bk = (1/M)*bk;
       tfs = tfs + bk* exp(1i*k*((2*pi)/Tp)*t);
    end
end

function s = signal ( t )
    t1 = mod (t ,3) ;
    s = t1 .*( t1 <1) + ( t1 >=1) .*( t1 <2) ;
end
