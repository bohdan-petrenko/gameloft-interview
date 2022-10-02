WITH matches_with_score AS (
	select
	  host_team,
	  guest_team,
	  host_goals,
	  guest_goals,
	  case
	    when host_goals > guest_goals then 3::smallint
	    when host_goals = guest_goals then 1::smallint
	    else 0::smallint
	  end as host_score,
	  case
	    when guest_goals > host_goals then 3::smallint
	    when guest_goals = host_goals then 1::smallint
	    else 0::smallint
	  end as guest_score
	from matches
),
group_home_subtotal AS (
	select
	  host_team,
	  sum(host_goals) as goals_scored_at_home,
	  sum(guest_goals) as goals_missed_at_home,
      sum(host_score) as score_at_home
	from matches_with_score
	group by host_team
),
group_guest_subtotal AS (
	select
	  guest_team,
	  sum(guest_goals) as goals_scored_as_guest,
	  sum(host_goals) as goals_missed_as_guest,
      sum(guest_score) as score_as_guest
	from matches_with_score
	group by guest_team
)
select
  name as team_name,
  goals_scored_at_home + goals_scored_as_guest as goals_for,
  goals_missed_at_home + goals_missed_as_guest as goals_against,
  score_at_home + score_as_guest as points
from teams
  inner join group_home_subtotal on teams.id = group_home_subtotal.host_team
  inner join group_guest_subtotal on teams.id = group_guest_subtotal.guest_team
order by points desc, goals_for desc