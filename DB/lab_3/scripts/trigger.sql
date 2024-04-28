CREATE OR REPLACE FUNCTION check_task_date()
RETURNS TRIGGER AS $$
BEGIN
  IF NEW.start_date < (SELECT start_date FROM task WHERE id = NEW.task_id) THEN
    RAISE EXCEPTION 'start_date in scientist_task cannot by earlier than start_date in task';
  END IF;
  IF NEW.end_date > (SELECT end_date FROM task WHERE id = NEW.task_id) THEN
    RAISE EXCEPTION 'end_date in scientist_task cannot by later than end_date in task';
  END IF;
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER task_date_trigger
AFTER INSERT ON scientist_task
FOR EACH ROW
EXECUTE FUNCTION check_task_date();
