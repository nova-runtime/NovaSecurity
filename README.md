# 🔐 NovaSecurity

**NovaSecurity** ist ein modernes **Security- & Protection-Plugin** für **Spigot & Paper**.

Ziel des Projekts ist es, Minecraft-Server **stabiler**, **sicherer** und **resistenter gegen Exploits & Crashes** zu machen – ohne unnötigen Overhead oder komplizierte Konfiguration.

> 🛡️ Security-first • ⚡ Performance-schonend • 🔌 Plugin-basiert • 🌐 Network-ready

---

## ✨ Features (aktuell & geplant)

### 🔐 Core-Security

* Schutz vor **bekannten Crash-Exploits** (Books, NBT, Packets)
* Validierung von **Client-Daten & Payloads**
* Sichere Kick-/Block-Mechanismen statt Server-Crashes

### ⚡ Performance-freundlich

* Kein permanenter Tick-Overhead
* Event- & Packet-basierte Checks
* Fokus auf Stabilität bei hoher Spielerzahl

### ⚙️ Konfigurierbar

* Zentrale `config.yml`
* Alle Schutzmechanismen einzeln aktivier-/deaktivierbar
* Serverfreundliche Default-Werte

### 🌐 Netzwerk-Vorbereitung

* Spigot & Paper Support
* Architektur vorbereitet für **Bungee/Velocity** (später)
* Einheitliches Verhalten über mehrere Server hinweg

---

## 🧩 Unterstützte Plattformen

* ✅ **Paper** (empfohlen)
* ✅ **Spigot**
* ⏳ **Bungee / Velocity** (geplant)

---

## 🛠️ Installation

1. Lade die neueste `NovaSecurity.jar` herunter
2. Lege sie in den `plugins/`-Ordner deines Servers
3. Starte den Server neu
4. Konfiguriere `plugins/NovaSecurity/config.yml`

---

## ⚙️ Beispiel-Konfiguration

```yml
nova:
  security:
    book-crash-protection: true
    nbt-size-limit: true
    packet-rate-limit: true

  punishments:
    kick-message: "§cUngültige Client-Daten erkannt"

  logging:
    log-to-console: true
```

---

## 📊 Commands & Permissions

### 🔹 Commands

* `/novasecurity` – Plugin-Info & Status
* `/novasecurity reload` – Konfiguration neu laden

### 🔹 Permissions

* `novasecurity.admin` – Admin-Zugriff

---

## 🧠 Projektphilosophie

NovaSecurity verfolgt einen klaren Ansatz:

* **Security im Core denken – aber als Plugin umsetzen**
* Keine unnötigen Features
* Kein AntiCheat-Ersatz, sondern **reiner Schutz & Stabilität**
* Sauberer Code, nachvollziehbare Logik

---

## 🗺️ Roadmap

* [ ] Book- & NBT-Exploit-Schutz
* [ ] Packet Rate Limiting
* [ ] Config Reload ohne Neustart
* [ ] Bungee/Velocity-Modul
* [ ] Metrics & optionales Debug-Logging

---

## 📄 Lizenz

Dieses Projekt ist ein **privates Open-Source-Projekt** zu Lern- und Entwicklungszwecken.

---

## 🚀 Vision

**NovaSecurity** soll eine **leichte, verlässliche Sicherheits-Basis** für Minecraft-Server jeder Größe werden – von kleinen Community-Servern bis hin zu größeren Netzwerken.

Built with ❤️ & Java.
