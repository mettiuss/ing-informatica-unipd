t = -10:0.01:10;
x = cos(2*pi*t + pi/2);
y1 = sin(pi*t + pi/3);
y2 = sin(2*t + pi/3);

figure
subplot (2 ,1 ,1)
plot (t ,x ,t , y1 ,t , x + y1 )
grid on
title ('\ omega_0 =\ pi')
legend ('x(t)','y(t)', 'z(t)')
subplot (2 ,1 ,2)
plot (t ,x ,t , y2 ,t , x + y2 )
grid on
title ('\ omega_0 =2 ')
