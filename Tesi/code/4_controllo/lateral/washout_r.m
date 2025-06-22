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
sys_tf = tf(sys);


washout_1 = tf([1 0], [1 1/3]);
washout_2 = tf([1 0], [1 1/5]);
washout_3 = tf([1 0], [1 1/10]);

c_0 = tf(-3.02, [0.03268 0.4268 1]);
c_1 = tf(-1.635, [0.059 0.69 1]);
c_2 = tf(-2.106, [0.047 0.57 1]);
c_3 = tf(-2.614, [0.039 0.49 1]);


sys_cl_0 = feedback(sys_tf * a , c_0);
sys_cl_1 = feedback(sys_tf * a, c_1*washout_1);
sys_cl_2 = feedback(sys_tf * a, c_2*washout_2);
sys_cl_3 = feedback(sys_tf * a, c_3*washout_3);


[y_0,t_0] = impulse(sys_cl_0, 16);
[y_1,t_1] = impulse(sys_cl_1, 16);
[y_2,t_2] = impulse(sys_cl_2, 16);
[y_3,t_3] = impulse(sys_cl_3, 16);

% --- Time-Domain Impulse Response ---
h = figure;
hold on
plot(t_1,  squeeze(y_1), 'LineWidth',2, 'Color', '#EDB120');
plot(t_2,  squeeze(y_2), 'LineWidth',2, 'Color', '#D95319');
plot(t_3,  squeeze(y_3), 'LineWidth',2, 'Color', '#7E2F8E');
plot(t_0,  squeeze(y_0), '--', 'LineWidth',2, 'Color', '#0072BD');
legend('C_{W, 1}(s)','C_{W, 2}(s)','C_{W, 3}(s)', 'C_2(s)', 'Location','southeast')
%fontsize(12,"points");
xlabel('Tempo (s)');
ylabel('r (rad/s)')
grid on;

set(h,'Position', [0 0 500 200]);
set(h,'Units','Inches');
pos = get(h,'Position');
set(h,'PaperPositionMode','Auto','PaperUnits','Inches','PaperSize',[pos(3), pos(4)])
print(h,'mode','-dpdf','-r0')
