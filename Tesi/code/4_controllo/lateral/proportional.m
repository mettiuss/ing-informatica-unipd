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

sys_feedback_1 = feedback(sys, -2.6981);
sys_feedback_2 = feedback(sys, tf(-3.022, [0.3268, 1]));

%[y_1,t_1] = impulse(sys_feedback_1);
%[y_2,t_2] = impulse(sys_feedback_2);
[y_1,t_1] = step(sys_feedback_1);
[y_2,t_2] = step(sys_feedback_2);


% --- Time-Domain Impulse Response ---
h = figure;
hold on;
plot(t_1,  squeeze(y_1),'--', 'LineWidth',2);
plot(t_2,  squeeze(y_2), 'LineWidth',2, 'Color', '#D20000');
%fontsize(12,"points");
xlabel('Tempo (s)');
ylabel('r (rad/s))')
grid on;

legend("C_1(s)", "C_2(s)")


set(h,'Position', [0 0 500 300]);
set(h,'Units','Inches');
pos = get(h,'Position');
set(h,'PaperPositionMode','Auto','PaperUnits','Inches','PaperSize',[pos(3), pos(4)])
print(h,'output','-dpdf','-r0')