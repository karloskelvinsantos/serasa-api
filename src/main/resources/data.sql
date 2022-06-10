INSERT INTO jwt_token (expiration, secret_key, active) VALUES (86400000, 'rm''!@N=Ke!~p8VTA2ZRK~nMDQX5Uvm!m''D&]{@Vr?G;2?XhbC:Qa#9#eMLN\}x3?JR3.2zr~v)gYF^8\:8>:XfB:Ww75N/emt9Yj[bQMNCWwW\J?N,nvH.<2\.r~w]*e~vgak)X"v8H`MH/7"2E`,^k@n<vE-wD3g9JWPy;CrY*.Kd2_D])=><D?YhBaSua5hW%{2]_FVXzb9`8FH^b[X3jzVER&:jw2<=c38=>L/zBq`}C6tT*cCSVC^c]-L}&/', true);

insert into serasa_authority(id, authority) values (1, 'ADMIN');

insert into serasa_user(id, username, password, enabled) values (1, 'admin', '$2a$10$S0V4vyd/AKBYA75ToUVnDOPJoyar0EsAAjg/zUaNZx3CHXlvHALRC', true);

insert into user_authority(user_id, authority_id) values (1, 1);