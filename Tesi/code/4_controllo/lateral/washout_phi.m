% Define system matrices
A = [-0.0558 -1 0.0802 0.0416;
    0.598 -0.115 -0.0318 0;
    -3.05 0.388 -0.465 0;
    0 0.0805 1 0];

B=[ .00729  0;
   -0.475   0.00775;
    0.153   0.143;
     0      0];
C=[0 1 0 0;
   0 0 0 1];

d=[0 0;
   0 0];

% Create state-space system
sys_open = ss(A, B, C, d);

a = tf(10, [1 10]);

c = tf(-3.0221, [1/3.045, 1]);
c_w = tf(-1.635, [0.59, 1]);

w = tf([1 0], [1, 1/3]);

sys_washout = feedback(sys_open, c_w*w, 1, 1);
sys_simple = feedback(sys_open, c, 1, 1);

[y1, t1] = impulse(sys_open(2, 2));
%[y2, t2] = impulse(sys_simple(2, 2));
[y2, t2] = impulse(sys_washout(2, 2));


% --- Time-Domain Impulse Response ---
h = figure;
plot(t1, y1, 'b--', t2, y2, 'r', 'LineWidth',2);
fontsize(12,"points");
xlabel('Tempo (s)');
ylabel('\phi(t) rad');
legend('Catena Aperta', 'C_{W, 1}(s)');
grid on;

axis([0 20 -0.1 0.4])

set(h,'Position', [0 0 500 250]);
set(h,'Units','Inches');
pos = get(h,'Position');
set(h,'PaperPositionMode','Auto','PaperUnits','Inches','PaperSize',[pos(3), pos(4)])
print(h,'BIBO_instabile','-dpdf','-r0')
