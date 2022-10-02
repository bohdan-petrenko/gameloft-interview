CREATE TABLE IF NOT EXISTS public.teams
(
    id integer NOT NULL,
    name character varying NOT NULL,
    CONSTRAINT teams_pkey PRIMARY KEY (id)
)

CREATE TABLE IF NOT EXISTS public.matches
(
    match_id integer NOT NULL,
    host_team integer NOT NULL,
    guest_team integer NOT NULL,
    host_goals smallint NOT NULL DEFAULT 0,
    guest_goals smallint NOT NULL DEFAULT 0,
    CONSTRAINT matches_pkey PRIMARY KEY (match_id),
    CONSTRAINT guest_team_fk FOREIGN KEY (guest_team)
        REFERENCES public.teams (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT host_team_fk FOREIGN KEY (host_team)
        REFERENCES public.teams (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

INSERT INTO public.teams(id, name)
	VALUES (1, 'FC Barcelona');
INSERT INTO public.teams(id, name)
	VALUES (2, 'Paris Saint-Germain');
INSERT INTO public.teams(id, name)
	VALUES (3, 'AFC Ajax');
INSERT INTO public.teams(id, name)
	VALUES (4, 'APOEL FC');

INSERT INTO public.matches(
	match_id, host_team, guest_team, host_goals, guest_goals)
	VALUES (1, 1, 2, 3, 1);
INSERT INTO public.matches(
	match_id, host_team, guest_team, host_goals, guest_goals)
	VALUES (2, 1, 3, 3, 1);
INSERT INTO public.matches(
	match_id, host_team, guest_team, host_goals, guest_goals)
	VALUES (3, 1, 4, 1, 0);
INSERT INTO public.matches(
	match_id, host_team, guest_team, host_goals, guest_goals)
	VALUES (4, 2, 1, 3, 2);
INSERT INTO public.matches(
	match_id, host_team, guest_team, host_goals, guest_goals)
	VALUES (5, 2, 3, 3, 1);
INSERT INTO public.matches(
	match_id, host_team, guest_team, host_goals, guest_goals)
	VALUES (6, 2, 4, 1, 0);
INSERT INTO public.matches(
	match_id, host_team, guest_team, host_goals, guest_goals)
	VALUES (7, 3, 1, 0, 2);
INSERT INTO public.matches(
	match_id, host_team, guest_team, host_goals, guest_goals)
	VALUES (8, 3, 2, 1, 1);
INSERT INTO public.matches(
	match_id, host_team, guest_team, host_goals, guest_goals)
	VALUES (9, 3, 4, 4, 0);
INSERT INTO public.matches(
	match_id, host_team, guest_team, host_goals, guest_goals)
	VALUES (10, 4, 1, 0, 4);
INSERT INTO public.matches(
	match_id, host_team, guest_team, host_goals, guest_goals)
	VALUES (11, 4, 2, 0, 1);
INSERT INTO public.matches(
	match_id, host_team, guest_team, host_goals, guest_goals)
	VALUES (12, 4, 3, 1, 1);