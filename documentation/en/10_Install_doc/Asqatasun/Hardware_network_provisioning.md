# Hardware provisioning for Asqatasun

## Minimal hardware requirements

* RAM: 4 Gb
* CPU: dual core (the more, the faster)
* Disk: 10Gb

## Notes on disk performance / usage

* For large (>10000 pages) and repetitive audits, a hardware RAID controller
increases performances in a really significant way !
* Statistically speaking, an audited page is about 0.3Mb on disk.

## Network flow

| Source host    | Destination host | Destination port | Detail             |
|:---------------|:-----------------|:-----------------|:-------------------|
| Asqatasun host | the internet     | 80               | Site to be audited |
| Asqatasun host | the internet     | 443              | Site to be audited |
| Asqatasun host | Asqatasun host   | 8009             | AJP connector      |
| Asqatasun host | Asqatasun host   | 3306             | Mysql              |
| Asqatasun host | SMTP host        | 25 / 465         | SMTP               |
| Asqatasun host | Asqatasun host   | 7055 to 7085     | Embedded Firefox   |

## Next step

Go to [Download Asqatasun](Download.md).