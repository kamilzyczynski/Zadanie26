insert into match_details (odd_teamA, odd_teamB, odd_draw) VALUES
                                                               (2.20, 3.75, 3.4),
                                                               (2.96, 3.5, 3.8),
                                                               (2.05, 3.4, 3.1),
                                                               (2.11, 3.35, 2.92),
                                                               (2.24, 3.25, 3.12),
                                                               (2.33, 3.75, 3.32),
                                                               (2.45, 3.25, 3.52),
                                                               (2.51, 3.55, 4.2),
                                                               (2.8, 3.15, 4.32),
                                                               (2.7, 3.05, 2.2);
insert into match (teamA, teamB, date, betting_closed, match_details_id)
VALUES
('FC Barcelona', 'Real Madryt', '2023-08-08', false, 1),
('Tottenham Spurs', 'Liverpool FC', '2023-08-26', false, 2),
('Manchester United', 'Manchester City', '2023-08-27', false, 3),
('Chelsea Londyn', 'Arsenal Londyn', '2023-08-15', false, 4),
('Bayern Monachium', 'Borussia Dortmund', '2023-08-16', false, 5),
('Paris Saint Germain', 'Olympique Lyon', '2023-08-17', false, 6),
('Ajax Amsterdam', 'PSV Eindhoven', '2023-08-15', false, 7),
('AS Roma', 'Juventus Turyn', '2023-08-11', false, 8),
('AC Milan', 'Inter Mediolan', '2023-08-13', false, 9),
('Atletico Madryt', 'Sevilla FC', '2023-08-10', false, 10);

insert into bet (matchbet, stake, possible_win, match_id) VALUES
                                                              ('TEAM_A', 10, 10 * 2.7, 10),
                                                              ('DRAW', 20, 20 * 3.55, 8),
                                                              ('TEAM_B', 10, 10 * 3.32, 6),
                                                              ('TEAM_A', 15, 15 * 2.7, 10),
                                                              ('TEAM_B', 10, 10 * 3.12, 5),
                                                              ('TEAM_A', 10, 10 * 2.7, 10),
                                                              ('TEAM_B', 13, 13 * 4.2, 8),
                                                              ('TEAM_A', 30, 30 * 2.33, 6);
