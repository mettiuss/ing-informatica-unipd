% Define system matrices
A = [-0.0558 -1 0.0802 0.0416;
    0.598 -0.115 -0.0318 0;
    -3.05 0.388 -0.465 0;
    0 0.0805 1 0];
B = [0.00729; -0.475; 0.153; 0];
C = [0 1 0 0];
d = 0;

a = tf(10, [1 10]);

sys = ss(A,B,C,d);

sys_feedback = feedback(sys, -2.6981);

%[y,t] = impulse(sys_feedback);
[y,t] = step(sys_feedback);


% --- Time-Domain Impulse Response ---
h = figure;
plot(t,  squeeze(y), 'LineWidth',2);
%fontsize(12,"points");
xlabel('Tempo (s)');
ylabel('r (rad/s))')
grid on;


set(h,'Position', [0 0 500 300]);
set(h,'Units','Inches');
pos = get(h,'Position');
set(h,'PaperPositionMode','Auto','PaperUnits','Inches','PaperSize',[pos(3), pos(4)])
print(h,'output','-dpdf','-r0')