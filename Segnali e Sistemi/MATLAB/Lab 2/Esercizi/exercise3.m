T_p = 3;
t = -4:0.01:5;
s = square_wave(t, T_p, 0.3);

plot(t, s)

function s = square_wave(t, T_p, d)
    t1 = mod ( t/T_p, 1) ;
    s = rect ( t1 / d ) + rect (( t1 -1) / d ) ;
end

function s = rect(t)
    s = 1*(abs(t) < 0.5) + 0.5*(t == -0.5) + 0.5*(t == 0.5);
end