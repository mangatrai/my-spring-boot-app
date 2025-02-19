CREATE TABLE ks1.auditing_events (
    event_identifier text,
    event_timestamp timestamp,
    event_state text,
    event_name text,
    event_payload_url text,
    id uuid,
    PRIMARY KEY ((event_identifier), event_state)
);

CREATE CUSTOM INDEX ON auditing_events (event_state) USING 'StorageAttachedIndex';
CREATE CUSTOM INDEX ON auditing_events (event_name) USING 'StorageAttachedIndex';

INSERT INTO ks1.auditing_events (
    event_identifier,
    event_timestamp,
    event_state,
    event_name,
    event_payload_url,
    id
) VALUES (
    '0058',
    '2024-05-01T12:00:00Z',
    'VALIDATED',
    'dayforce_sync',
    'some link to the blob',
    1beb8199-453c-4bb2-8857-028045f2a771
);

select * from auditing_events where event_identifier='0058';


select * from auditing_events where event_state = 'VALIDATED'and event_identifier = '0058';


select * from auditing_events where event_state = 'VALIDATED';


select * from auditing_events where event_name = 'dayforce_sync';


select * from auditing_events where id = 1beb8199-453c-4bb2-8857-028045f2a771 and event_state = 'VALIDATED'and event_identifier = '0058';


select * from auditing_events where event_identifier = '0058' and id = 1beb8199-453c-4bb2-8857-028045f2a771 and event_state = 'VALIDATED';