# To create JMS Queue in Wildfly 

From jboss-cli run this command:

`jms-queue --profile=full add --queue-address=BMIQueue --entries=["java:/jms/queue/BMIQueue"]`

Also run wildfly server in standalone-full mode.